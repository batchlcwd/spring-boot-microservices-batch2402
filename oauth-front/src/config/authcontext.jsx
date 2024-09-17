import { createContext, useContext, useEffect, useState } from "react";
import keycloak from "./keycloak";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [keycloakInstance, setKeycloakInstance] = useState(null);

  // jaise hi application keycloak :  banda login hai ki nhi login

  useEffect(() => {
    // call hoga on startup

    keycloak
      .init({
        onLoad: "check-sso",
        pkceMethod: "S256",
      })
      .then((authenticated) => {
        console.log("user login success");
        console.log(authenticated);
        setIsAuthenticated(authenticated);
        setKeycloakInstance(keycloak);
        console.log(keycloakInstance);
      })
      .catch((error) => {
        console.log("error", error);
        console.log("user login fail");
      });
  }, []);

  return (
    <AuthContext.Provider
      value={{
        isAuthenticated,
        setIsAuthenticated,
        keycloak: keycloakInstance,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
