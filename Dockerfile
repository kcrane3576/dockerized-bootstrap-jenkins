FROM jenkins/jenkins:lts

# Pre-install Jenkins plugins
COPY configuration/plugins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Pre-configure Jenkins admin user
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

COPY groovy/bootstrap/basic-security.groovy /usr/share/jenkins/ref/init.groovy.d/

ENV JENKINS_USER admin
ENV JENKINS_PASS admin

COPY groovy/bootstrap/default-user.groovy /usr/share/jenkins/ref/init.groovy.d/

USER root
RUN apt-get update && apt-get install -y maven