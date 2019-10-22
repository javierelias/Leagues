package league.clients;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class MicroserviceClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceClient.class);

	private static final String MICROSERVICES_TOKEN = "X-Auth-Token";

	@Autowired
	private HttpServletRequest servletResquest;

	@Value("${microservices.token-user:}")
	private String microserviceToken;

	private int timeout = 5000;

	private RestTemplate restTemplate;

	private CustomHeaderInterceptor interceptor;

	private String baseUrl;

	public MicroserviceClient(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public <T> ResponseEntity<T> doGet(String url, Object body, Class<T> responseClass) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + url);

		if (null != body)
			populateUrlParams(builder, body);

		return restTemplate.getForEntity(builder.build().encode().toUri(), responseClass);
	}

	public <T> ResponseEntity<T> doGet(String url, Class<T> responseClass) {
		return doGet(url, null, responseClass);
	}

	public <T> ResponseEntity<T> doPost(String url, Object body, Class<T> responseClass) {
		return restTemplate.postForEntity(baseUrl + url, body, responseClass);
	}

	public <T> ResponseEntity<T> doPost(String url, Class<T> responseClass) {
		return doPost(url, null, responseClass);
	}

	public void doPut(String url, Object body) {
		restTemplate.put(baseUrl + url, body);
	}

	public void doDelete(String url) {
		restTemplate.delete(baseUrl + url);
	}

	private void populateUrlParams(UriComponentsBuilder builder, Object o) {
		populateUrlParams(builder, o.getClass(), o);
	}

	private void populateUrlParams(UriComponentsBuilder builder, Class<?> clazz, Object o) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
				try {
					Object obj = method.invoke(o);
					String fieldName = method.getName().replaceFirst("get", "").replaceFirst("is", "");
					fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1, fieldName.length());
					if (obj instanceof List) {
						obj = Arrays.toString(((List<?>) obj).toArray()).replaceAll("\\[", "").replaceAll("\\]", "");
					}
					if (obj != null)
						builder.queryParam(fieldName, obj);
				} catch (Exception e) {
					LOGGER.warn("populateUrlParams() - Error al generar Parámetros [{}]", e);
				}
			}
		}

		if (null != clazz.getSuperclass() && clazz.getSuperclass() != Object.class) {
			populateUrlParams(builder, clazz.getSuperclass(), o);
		}
	}

	public <T> List<T> doGetList(String url, Object body, Class<T[]> responseClass) {
		ResponseEntity<T[]> response = doGet(url, body, responseClass);
		List<T> list = Arrays.asList(response.getBody());
		return list;
	}

	public <T> List<T> doPostList(String url, Object body, Class<T[]> responseClass) {
		ResponseEntity<T[]> response = doPost(url, body, responseClass);
		List<T> list = Arrays.asList(response.getBody());
		return list;
	}

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		this.interceptor = new CustomHeaderInterceptor();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(interceptor);
		this.restTemplate.setInterceptors(interceptors);

		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout(timeout);
		factory.setReadTimeout(timeout);

		this.restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(factory));
	}

	class CustomHeaderInterceptor implements ClientHttpRequestInterceptor {

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
				throws IOException {
			HttpHeaders headers = request.getHeaders();
			headers.add(MICROSERVICES_TOKEN, microserviceToken);
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			return execution.execute(request, body);
		}
	}
}
