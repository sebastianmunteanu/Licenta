import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";
import Icon from "@mui/material/Icon";
import Tooltip from "@mui/material/Tooltip";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";

// Material Dashboard 2 React context
import MDInput from "components/MDInput";
import { useMaterialUIController } from "context";
import { useSensorContext } from "context/SensorContext";
import { useState } from "react";

function Messages() {
  const [controller] = useMaterialUIController();
  const { darkMode } = controller;
  const [onMessage, setOnMessage] = useState("");
  const [offMessage, setOffMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const {
    state: { currentSensor },
    editOnMessage,
    editOffMessage
  } = useSensorContext();

  const resetError = () => {
    setErrorMessage("");
  };

  const genereteErrorMessage = () => {
    setErrorMessage("Introduceti un mesaj valid");
  };

  function handleOnMessage(e) {
    setOnMessage(e.target.value);
    resetError();
  }

  const handleOnClick = async () => {
    if (!onMessage) {
      genereteErrorMessage();
      return;
    }
    await editOnMessage(onMessage);
    resetError();
    setOnMessage("");
  };

  function handleOffMessage(e) {
    setOffMessage(e.target.value);
    resetError();
  }

  const handleOffClick = async () => {
    if (!offMessage) {
      genereteErrorMessage();
      return;
    }
    await editOffMessage(offMessage);
    resetError();
    setOffMessage("");
  };
  return (
    <Card id="delete-account">
      <MDBox pt={2} px={2} display="flex" justifyContent="center" alignItems="center">
        <MDTypography variant="h6" fontWeight="medium">
          Mesaje
        </MDTypography>
      </MDBox>
      <MDBox pt={2} px={2} display="flex" justifyContent="center" alignItems="center">
        {errorMessage && (
          <MDTypography variant="h6" fontWeight="medium" color="error">
            {errorMessage}
          </MDTypography>
        )}
      </MDBox>
      <MDBox p={2}>
        <Grid container spacing={2}>
          <Grid item xs={12} md={6}>
            <MDBox
              borderRadius="lg"
              display="flex"
              justifyContent="space-between"
              alignItems="center"
              p={1}
              sx={{
                border: ({ borders: { borderWidth, borderColor } }) =>
                  `${borderWidth[1]} solid ${borderColor}`
              }}
            >
              <MDTypography color="success"> ON </MDTypography>
              <Grid>
                <MDTypography variant="h6" fontWeight="medium">
                  {currentSensor.onMessage}
                </MDTypography>
                <MDInput value={onMessage} onChange={handleOnMessage}></MDInput>
              </Grid>
              <MDBox lineHeight={0} color={darkMode ? "white" : "dark"}>
                <Tooltip title="Edit" placement="top">
                  <Icon sx={{ cursor: "pointer" }} fontSize="small" onClick={handleOnClick}>
                    edit
                  </Icon>
                </Tooltip>
              </MDBox>
            </MDBox>
          </Grid>
          <Grid item xs={12} md={6}>
            <MDBox
              borderRadius="lg"
              display="flex"
              justifyContent="space-between"
              alignItems="center"
              p={1}
              sx={{
                border: ({ borders: { borderWidth, borderColor } }) =>
                  `${borderWidth[1]} solid ${borderColor}`
              }}
            >
              <MDTypography color="error"> OFF </MDTypography>
              <Grid>
                <MDTypography variant="h6" fontWeight="medium">
                  {currentSensor.offMessage}
                </MDTypography>
                <MDInput value={offMessage} onChange={handleOffMessage}></MDInput>
              </Grid>
              <MDBox lineHeight={0} color={darkMode ? "white" : "dark"}>
                <Tooltip title="Edit" placement="top">
                  <Icon sx={{ cursor: "pointer" }} fontSize="small" onClick={handleOffClick}>
                    edit
                  </Icon>
                </Tooltip>
              </MDBox>
            </MDBox>
          </Grid>
        </Grid>
      </MDBox>
    </Card>
  );
}

export default Messages;
