package com.netflix.spinnaker.halyard.cli.command.v1.config.providers.cloudrun;

public class CloudrunCommandProperties {
  static final String LOCAL_REPOSITORY_DIRECTORY_DESCRIPTION =
      "A local directory to be used to stage source files"
          + " for Cloud Run deployments within Spinnaker's Clouddriver microservice.";
  static final String GIT_HTTPS_USERNAME_DESCRIPTION =
      "A username to be used when connecting with a remote git" + " repository server over HTTPS.";
  static final String GIT_HTTPS_PASSWORD_DESCRIPTION =
      "A password to be used when connecting with a remote git" + " repository server over HTTPS.";
  static final String GITHUB_OAUTH_ACCESS_TOKEN_DESCRIPTION =
      "An OAuth token provided by Github for connecting to "
          + " a git repository over HTTPS."
          + " See https://help.github.com/articles/creating-an-access-token-for-command-line-use for more information.";
  static final String SSH_PRIVATE_KEY_FILE_PATH =
      "The path to an SSH private key to be used when"
          + " connecting with a remote git repository over SSH.";
  static final String SSH_PRIVATE_KEY_PASSPHRASE =
      "The passphrase to an SSH private key to be used"
          + " when connecting with a remote git repository over SSH.";
  static final String SSH_KNOWN_HOSTS_FILE_PATH =
      "The path to a known_hosts file to be used when connecting with"
          + " a remote git repository over SSH.";
  static final String SSH_TRUST_UNKNOWN_HOSTS =
      "Enabling this flag will allow Spinnaker to connect"
          + " with a remote git repository over SSH without verifying the server's IP address"
          + " against a known_hosts file.";
  static final String GCLOUD_RELEASE_TRACK =
      "The gcloud release track (ALPHA, BETA, or STABLE) that Spinnaker"
          + " will use when deploying to Cloud Run.";
  static final String SERVICES =
      "A list of regular expressions. Any service matching one of these regexes "
          + "will be indexed by Spinnaker.";
  static final String VERSIONS =
      "A list of regular expressions. Any version matching one of these regexes "
          + "will be indexed by Spinnaker.";
  static final String OMIT_SERVICES =
      "A list of regular expressions. Any service matching one of these regexes "
          + "will be ignored by Spinnaker.";
  static final String OMIT_VERSIONS =
      "A list of regular expressions. Any version matching one of these regexes "
          + "will be ignored by Spinnaker.";
  static final String CACHING_INTERVAL_SECONDS =
      "The interval in seconds at which Spinnaker will poll for updates "
          + "in your Cloud Run clusters.";
}
