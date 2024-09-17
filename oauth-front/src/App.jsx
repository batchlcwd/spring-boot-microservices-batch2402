import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { useAuth } from "./config/authcontext";

function App() {
  const [count, setCount] = useState(0);

  const { isAuthenticated, setIsAuthenticated, keycloak } = useAuth();
  console.log(keycloak);

  console.log(isAuthenticated);
  return (
    <>
      {isAuthenticated ? (
        <div>
          <h1>Welcome to OAuth Front-End</h1>
          <h1> Username : {keycloak.tokenParsed.preferred_username}</h1>
          <h2>Email : {keycloak.tokenParsed.email}</h2>
          <h2>
            Roles :{" "}
            {keycloak.realmAccess.roles.map((item) => (
              <span>{item} ,</span>
            ))}
          </h2>
          <h1> User Information </h1>
          <h1>
            {isAuthenticated
              ? "User is Authenticated"
              : "User is not authenticated"}
          </h1>
          <button
            onClick={() => {
              keycloak.logout();
            }}
          >
            Logout
          </button>
        </div>
      ) : (
        <div>
          <h1> Login Required </h1>
          <button
            onClick={() => {
              keycloak.login();
            }}
          >
            Login
          </button>
        </div>
      )}
    </>
  );
}

export default App;
