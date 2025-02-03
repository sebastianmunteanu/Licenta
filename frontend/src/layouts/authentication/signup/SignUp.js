import { useState } from "react";
import { Link } from "react-router-dom";

// @mui material components
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import MDInput from "components/MDInput";
import MDTypography from "components/MDTypography";

// Authentication layout components
import CoverLayout from "layouts/authentication/components/CoverLayout";

import { useNavigate } from "react-router-dom";

// Images
import { addNewUser } from "api/authentication/signin";
import bgImage from "assets/images/bg-sign-up-cover.jpeg";

function SignUp() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleNameInput = (e) => {
    setName(e.target.value);
  };

  const handleEmailInput = (e) => {
    setEmail(e.target.value);
  };

  const handlePasswordInput = (e) => {
    setPassword(e.target.value);
  };

  const handleNewUserAdd = async (e) => {
    e.preventDefault();
    if (!email || !password || !name) {
      setErrorMessage("Nu ai completat toate campurile.");
      return;
    }
    const response = await addNewUser(name, email, password);
    if (response.ok) {
      navigate("/authentication/login");
    } else {
      setErrorMessage("User existent in baza de date.");
    }
  };

  return (
    <CoverLayout image={bgImage}>
      <Card>
        <MDBox
          variant="gradient"
          bgColor="info"
          borderRadius="lg"
          coloredShadow="success"
          mx={2}
          mt={-3}
          p={3}
          mb={1}
          textAlign="center"
        >
          <MDTypography variant="h4" fontWeight="medium" color="white" mt={1}>
            Creează cont
          </MDTypography>
          <MDTypography display="block" variant="button" color="white" my={1}>
            Introduceti datele solicitate
          </MDTypography>
        </MDBox>
        <MDBox ml={8}>
          {errorMessage && (
            <MDBox>
              <MDTypography color="error" variant="body2">
                {errorMessage}
              </MDTypography>
            </MDBox>
          )}
        </MDBox>

        <MDBox pt={4} pb={3} px={3}>
          <MDBox>
            <MDBox mb={2}>
              <MDInput
                type="name"
                label="Nume"
                variant="standard"
                fullWidth
                onChange={handleNameInput}
              />
            </MDBox>
            <MDBox mb={2}>
              <MDInput
                type="email"
                label="Email"
                variant="standard"
                fullWidth
                onChange={handleEmailInput}
              />
            </MDBox>
            <MDBox mb={2}>
              <MDInput
                type="password"
                label="Parola"
                variant="standard"
                fullWidth
                onChange={handlePasswordInput}
              />
            </MDBox>
            <MDBox mt={4} mb={1}>
              <MDButton variant="gradient" color="info" fullWidth onClick={handleNewUserAdd}>
                Inregistare
              </MDButton>
            </MDBox>
            <MDBox mt={3} mb={1} textAlign="center">
              <MDTypography variant="button" color="text">
                Ai deja cont?{" "}
                <MDTypography
                  component={Link}
                  to="/authentication/sign-in"
                  variant="button"
                  color="info"
                  fontWeight="medium"
                  textGradient
                >
                  Login
                </MDTypography>
              </MDTypography>
            </MDBox>
          </MDBox>
        </MDBox>
      </Card>
    </CoverLayout>
  );
}

export default SignUp;
