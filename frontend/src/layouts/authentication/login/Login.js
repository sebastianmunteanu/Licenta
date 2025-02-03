// react-router-dom components
import { useState } from "react";
import { Link } from "react-router-dom";
// @mui material components
import Card from "@mui/material/Card";

// @mui icons

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import MDInput from "components/MDInput";
import MDTypography from "components/MDTypography";
import { useAuthContext } from "context/AuthContext";

// Authentication layout components
import BasicLayout from "layouts/authentication/components/BasicLayout";

// Images
import bgImage from "assets/images/smarthome.jpg";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const { login, authMessage, setAuthMessage } = useAuthContext();

  const handleLogin = async (e) => {
    e.preventDefault();
    if (!email || !password) {
      setErrorMessage("Nu ai completat toate campurile.");
      return;
    }
    login(email, password);
  };

  const resetInput = () => {
    setErrorMessage("");
    setAuthMessage("");
  };

  const handleEmailInput = (e) => {
    setEmail(e.target.value);
    resetInput();
  };

  const handlePasswordInput = (e) => {
    setPassword(e.target.value);
    resetInput();
  };

  return (
    <BasicLayout image={bgImage}>
      <Card>
        <MDBox
          variant="gradient"
          bgColor="info"
          borderRadius="lg"
          coloredShadow="info"
          mx={2}
          mt={-3}
          p={2}
          mb={1}
          textAlign="center"
        >
          <MDTypography variant="h4" fontWeight="medium" color="white" mt={1}>
            Autentificare
          </MDTypography>
        </MDBox>
        <MDBox pt={4} pb={3} px={3}>
          <MDBox>
            <MDBox mb={2}>
              <MDInput type="email" label="Email" fullWidth onChange={handleEmailInput} />
            </MDBox>
            <MDBox mb={2}>
              <MDInput type="password" label="Parola" fullWidth onChange={handlePasswordInput} />
            </MDBox>
            {errorMessage && (
              <MDBox mb={2}>
                <MDTypography color="error" variant="body2">
                  {errorMessage}
                </MDTypography>
              </MDBox>
            )}
            {authMessage && (
              <MDBox mb={2}>
                <MDTypography color="error" variant="body2">
                  {authMessage}
                </MDTypography>
              </MDBox>
            )}
            <MDBox mt={4} mb={1}>
              <MDButton variant="gradient" color="info" fullWidth onClick={handleLogin}>
                login
              </MDButton>
            </MDBox>
            <MDBox mt={3} mb={1} textAlign="center">
              <MDTypography variant="button" color="text">
                Nu ai cont?{" "}
                <MDTypography
                  component={Link}
                  to="/authentication/sign-up"
                  variant="button"
                  color="info"
                  fontWeight="medium"
                  textGradient
                >
                  Creează-ți unul
                </MDTypography>
              </MDTypography>
            </MDBox>
          </MDBox>
        </MDBox>
      </Card>
    </BasicLayout>
  );
}

export default Login;
