import PropTypes from "prop-types";
import { createContext, useContext, useEffect, useMemo, useState } from "react";
import { useNavigate } from "react-router-dom";
const AuthContext = createContext();

function AuthProvider({ children }) {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [loading, setLoading] = useState(true);
  const [authMessage, setAuthMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    if (token) {
      setIsAuthenticated(true);
    } else {
      setIsAuthenticated(false);
    }
    setLoading(false);
  }, []);

  const login = async (email, password) => {
    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
      });
      if (response.ok) {
        const data = await response.json();
        sessionStorage.setItem("token", data.token);
        setIsAuthenticated(true);
        navigate("/dashboard");
      } else {
        setAuthMessage("Credentiale invalide.");
      }
    } catch {
      console.log("error");
    }
  };

  const logout = async () => {
    sessionStorage.removeItem("token");
    setIsAuthenticated(false);
    navigate("/authentication/login");
  };

  const contextValues = useMemo(() => {
    return {
      isAuthenticated,
      authMessage,
      login,
      logout,
      setAuthMessage,
      navigate
    };
  }, [isAuthenticated, authMessage]);

  if (loading) {
    return <div>Loading...</div>;
  }
  return <AuthContext.Provider value={contextValues}>{children}</AuthContext.Provider>;
}

function useAuthContext() {
  const context = useContext(AuthContext);
  if (context === undefined) throw new Error("not in context");
  return context;
}

AuthProvider.propTypes = {
  children: PropTypes.node
};

export { AuthProvider, useAuthContext };
