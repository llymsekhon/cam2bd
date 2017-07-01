package com.demo.camel;import org.apache.camel.builder.RouteBuilder;import org.apache.camel.model.rest.RestBindingMode;import org.slf4j.Logger;import org.slf4j.LoggerFactory;public class MainRouteBuilder extends RouteBuilder {	private static final Logger LOGGER = LoggerFactory			.getLogger(MainRouteBuilder.class);	@Override	public void configure() throws Exception {		restConfiguration().component("jetty").host("0.0.0.0")				.port(80)				.bindingMode(RestBindingMode.auto)				.dataFormatProperty("prettyPrint", "true")				.apiContextPath("/api-doc")				.apiProperty("api.title", "User API")				.apiProperty("api.version", "1.2.3")				.apiProperty("cors", "true");		rest("/say").description("User greeting service").get("/hello").description("Say Hello").to("direct:hello").responseMessage().code(200).endResponseMessage()					.get("/bye").description("Say bye").to("direct:bye");		//from("direct:hello").description("Service for hello").transform().constant("Hello World");		//from("direct:bye").description("Service for bye").transform().constant("Bye World");	}}