import PropTypes from "prop-types";

// @mui material components
import Card from "@mui/material/Card";
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import { Divider } from "@mui/material";
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import MDInput from "components/MDInput";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";
import { useState } from "react";

function PhoneNumberAddAlert({ color, icon, title, description }) {
  const { addSmsAlert } = useSensorContext();
  const [inputNumber, setInputNumber] = useState("");
  const [error, setError] = useState("");

  const validatePhoneNumber = (number) => {
    const phoneRegex = /^(\+?[0-9]{1,3})?[-. ]?(\(?[0-9]{3}\)?[-. ]?[0-9]{3}[-. ]?[0-9]{4})$/;
    return phoneRegex.test(number);
  };

  const handleClick = async () => {
    if (!validatePhoneNumber(inputNumber)) {
      setError("Introduceți un număr de telefon valid!");
      return;
    }
    setError("");
    await addSmsAlert(inputNumber);
    setInputNumber("");
  };

  function handleInputChange(e) {
    setInputNumber(e.target.value);
    if (error) {
      setError("");
    }
  }
  return (
    <Card>
      <MDBox p={2} mx={3} display="flex" justifyContent="center">
        <MDBox
          display="grid"
          justifyContent="center"
          alignItems="center"
          bgColor={color}
          color="white"
          width="2rem"
          height="2rem"
          shadow="md"
          borderRadius="lg"
          variant="gradient"
        >
          <Icon fontSize="default">{icon}</Icon>
        </MDBox>
      </MDBox>
      <MDBox pb={2} px={2} textAlign="center" lineHeight={1.25}>
        <MDTypography variant="h6" fontWeight="medium" textTransform="capitalize">
          {description}
        </MDTypography>
        {error && (
          <MDTypography variant="caption" color="error" fontWeight="regular">
            {error}
          </MDTypography>
        )}
        <MDInput value={inputNumber} onChange={handleInputChange}></MDInput>
        <Divider></Divider>
        <MDButton color="success" onClick={handleClick}>
          adauga alerta
        </MDButton>
      </MDBox>
    </Card>
  );
}

// Setting default values for the props of DefaultInfoCard
PhoneNumberAddAlert.defaultProps = {
  color: "info",
  description: ""
};

// Typechecking props for the DefaultInfoCard
PhoneNumberAddAlert.propTypes = {
  color: PropTypes.oneOf(["primary", "secondary", "info", "success", "warning", "error", "dark"]),
  icon: PropTypes.node.isRequired,
  title: PropTypes.string.isRequired,
  description: PropTypes.string
};

export default PhoneNumberAddAlert;
