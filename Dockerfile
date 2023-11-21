FROM jenkins/jenkins:lts@sha256:c2323612c5da6a2d37f2cd6147b004a4ccceca223a85c07c70bb7ae1f663666a

# Pre-install Jenkins plugins
COPY configuration/plugins/plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# Skip the set up process on initial start of Jenkins
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

# Configure basic security
COPY configuration/groovy/basic-security.groovy /usr/share/jenkins/ref/init.groovy.d/

# Pre-configure Jenkins admin user
ARG password
ENV JENKINS_USER admin
ENV JENKINS_PASS $password
COPY configuration/groovy/default-user.groovy /usr/share/jenkins/ref/init.groovy.d/

# Pre-configure Jenkins with a seed job
COPY configuration/groovy/seed.groovy /usr/share/jenkins/ref/init.groovy.d/

# Configure maven setup
USER root
RUN apt-get update && apt-get install -y maven