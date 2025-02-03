import { useAuthContext } from "context/AuthContext";
import PropTypes from "prop-types";
import { Navigate } from "react-router-dom";

// Componentă care protejează rutele
const ProtectedRoute = ({ children }) => {
  const { isAuthenticated } = useAuthContext();
  if (!isAuthenticated) {
    return <Navigate to="/authentication/login" />;
  }
  return children;
};

ProtectedRoute.propTypes = {
  children: PropTypes.node.isRequired
};

export default ProtectedRoute;
