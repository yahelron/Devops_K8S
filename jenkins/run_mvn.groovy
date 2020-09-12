def label = "maven_pod-${UUID.randomUUID().toString()}"
podTemplate(label: label, containers: [
		containerTemplate(name: 'maven', image: 'maven:3.3.9-jdk-8-alpine', ttyEnabled: true, command: '/bin/sh')]) {
	node(label) {
		stage("Checkout") {
			git clone "https://github.com/web3j/sample-project-maven.git"
		}
		stage("Build") {
			container('maven'){
				sh "mvn clean install"
			}
		}
	}
}
