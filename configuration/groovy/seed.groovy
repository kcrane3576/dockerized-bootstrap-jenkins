import hudson.model.*
import hudson.plugins.git.*;

import jenkins.model.*
import javaposse.jobdsl.plugin.*

def parent = Jenkins.getInstance()

def project = new FreeStyleProject(parent, "seedJob")

//
StringParameterDefinition params = new StringParameterDefinition("jobName", null, "The name of your repo (e.g. poc-micro)")
project.addProperty(new ParametersDefinitionProperty(params));

//TODO
project.scm = new GitSCM("https://github.com/kcrane3576/microservice-pipelines")
project.scm.branches = [new BranchSpec("*/part3")]

def dslScript = new ExecuteDslScripts()
dslScript.setTargets("dsl/seed.groovy")

project.getBuildersList().add(dslScript)

project.save()

parent.reload()



