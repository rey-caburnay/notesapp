plugins {
	java
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.exam.ota"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
//	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")

//	implementation ("io.springfox:springfox-swagger-ui:3.0.0")
	implementation ("org.springdoc:springdoc-openapi-ui:1.6.14")
	implementation ("org.springdoc:springdoc-openapi-data-rest:1.6.14")
	implementation ("org.springdoc:springdoc-openapi-webflux-core:1.6.14")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation ("org.mockito:mockito-core:3.6.28")
	testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
