/*
 * Copyright 2017 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.halyard.config.validate.v1.providers.cloudrun;

import com.netflix.spinnaker.clouddriver.cloudrun.security.CloudrunNamedAccountCredentials;
import com.netflix.spinnaker.halyard.config.model.v1.node.Validator;
import com.netflix.spinnaker.halyard.config.model.v1.providers.cloudrun.CloudrunAccount;
import com.netflix.spinnaker.halyard.config.problem.v1.ConfigProblemSetBuilder;
import com.netflix.spinnaker.halyard.core.problem.v1.Problem.Severity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CloudrunAccountValidator extends Validator<CloudrunAccount> {
  @Autowired String halyardVersion;

  @Override
  public void validate(ConfigProblemSetBuilder p, CloudrunAccount account) {
    String jsonKey = null;
    String jsonPath = account.getJsonPath();
    String project = account.getProject();
    String knownHostsPath = account.getSshKnownHostsFilePath();
    CloudrunNamedAccountCredentials cloudrunNamedAccountCredentials = null;

    if (knownHostsPath != null && !knownHostsPath.isEmpty()) {
      String knownHosts = validatingFileDecrypt(p, knownHostsPath);
      if (knownHosts == null) {
        return;
      }
      if (knownHosts.isEmpty()) {
        p.addProblem(Severity.WARNING, "The supplied known_hosts file is empty.");
      }
    }

    if (jsonPath != null && !jsonPath.isEmpty()) {
      jsonKey = validatingFileDecrypt(p, jsonPath);
      if (jsonKey == null) {
        return;
      }
      if (jsonKey.isEmpty()) {
        p.addProblem(Severity.WARNING, "The supplied credentials file is empty.");
      }
    }

    if (jsonPath != null && !jsonPath.isEmpty() && account.isSshTrustUnknownHosts()) {
      p.addProblem(
              Severity.WARNING,
              "You have supplied a known_hosts file path and set the `--ssh-trust-unknown-hosts` flag to true."
                  + " Spinnaker will ignore your `--ssh-trust-unknown-hosts` flag.")
          .setRemediation("Run `--ssh-trust-unknown-hosts false`.");
    }

    if (account.getProject() == null || account.getProject().isEmpty()) {
      p.addProblem(Severity.ERROR, "No cloudrun project supplied.");
      return;
    }

    /*   try {
          cloudrunNamedAccountCredentials =
              new CloudrunNamedAccountCredentials.Builder()
                  .setJsonKey(jsonKey)
                  .setProject(project)
                  .setRegion("halyard")
                  .setApplicationName("halyard " + halyardVersion)
                  .build(new CloudrunJobExecutor());

        } catch (Exception e) {
          p.addProblem(
              Severity.ERROR, "Error instantiating cloudrun credentials: " + e.getMessage() + ".");
          return;
        }

    try {
         // credentials.getCloudrun().apps().get(project).execute();
         // cloudrunNamedAccountCredentials.;
        } catch (GoogleJsonResponseException e) {
          if (e.getStatusCode() == 404) {
            p.addProblem(Severity.ERROR, "No cloudrun application found for project " + project + ".")
                .setRemediation(
                    "Run `gcloud app create --region <region>` to create an cloudrun application.");
          } else {
            p.addProblem(
                Severity.ERROR, "Failed to connect to cloudrun Admin API: " + e.getMessage() + ".");
          }
        } catch (Exception e) {
          p.addProblem(
              Severity.ERROR, "Failed to connect to cloudrun Admin API: " + e.getMessage() + ".");
        }*/
  }
}
