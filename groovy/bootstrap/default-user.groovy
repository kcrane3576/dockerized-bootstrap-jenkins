import jenkins.model.*
import hudson.security.*

// Retrieve environment variables
def env = System.getenv()

// Set up instance of Jenkins
def instance = Jenkins.getInstance()

// Configure security realm
if(!(instance.getSecurityRealm() instanceof HudsonPrivateSecurityRealm))
    instance.setSecurityRealm(new HudsonPrivateSecurityRealm(false))

// Configure authorization strategy
if(!(instance.getAuthorizationStrategy() instanceof GlobalMatrixAuthorizationStrategy))
    instance.setAuthorizationStrategy(new GlobalMatrixAuthorizationStrategy())

// Create user based on environment variables
def user = instance.getSecurityRealm().createAccount(env.JENKINS_USER, env.JENKINS_PASS)
user.save()

// Configure user to have admin rights
instance.getAuthorizationStrategy().add(Jenkins.ADMINISTER, env.JENKINS_USER)

instance.save()