#!groovy

import jenkins.model.*
import hudson.security.*

// Set up instance of Jenkins
def instance = Jenkins.getInstance()

// Configure Jenkins instance to use private security realm
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
instance.setSecurityRealm(hudsonRealm)

// Configure security to require user to be logged in
def strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()