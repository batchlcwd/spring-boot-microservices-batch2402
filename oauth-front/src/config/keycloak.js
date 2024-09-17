import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  realm: "dev",
  url: "http://localhost:9190",
  clientId: "elearn-app",
  pkceMethod: "S256",
});

export default keycloak;
