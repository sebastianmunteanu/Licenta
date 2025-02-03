import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";

// Material Dashboard 2 React components
import { fetchWithAuth } from "api/data/fetchWithAuth";
import { getAllPins } from "api/data/pin/getAllPins";
import { getWarningPins } from "api/data/pin/getWarningPins";
import MDAvatar from "components/MDAvatar";
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import PinsLegend from "./PinsLegend";

function Status() {
  const [conStatus, setConStatus] = useState(false);
  const [raspberryConn, setRaspberryConn] = useState(false);
  const [analoguePins, setAnaloguePins] = useState([]);
  const [digitalPins, setDigitalPins] = useState([]);
  const [warningPins, setWarningPins] = useState([]);
  const { setSelectedPin } = useSensorContext();
  const navigate = useNavigate();

  const checkConnectivity = async () => {
    try {
      const conn = await fetchWithAuth("check-conectivity");
      if (conn.status === 503) {
        setConStatus(false);
        return;
      }
      setConStatus(true);
    } catch (error) {
      setConStatus(false);
    }
  };

  const testRaspberryConnection = async () => {
    try {
      const conn = await fetchWithAuth("check-raspberry-con");
      if (conn.status === 503) {
        setRaspberryConn(false);
        return;
      }
      setRaspberryConn(true);
    } catch (error) {
      setRaspberryConn(false);
    }
  };

  const getPins = async () => {
    try {
      const response = await getAllPins();
      const warn = await getWarningPins();
      const analogue = response.filter((pin) => pin.pinType === "analogic");
      const digital = response.filter((pin) => pin.pinType === "digital");
      setAnaloguePins(analogue);
      setDigitalPins(digital);
      setWarningPins(warn);
    } catch (error) {
      console.error("Eroare la încărcarea pinilor:", error);
    }
  };

  const handleOnClick = (pin) => {
    setSelectedPin(pin);
    navigate("/adauga-senzor");
  };

  useEffect(() => {
    checkConnectivity();
    testRaspberryConnection();
    getPins();
  }, []);
  return (
    <MDBox position="relative" m={10}>
      <Card
        sx={{
          position: "relative",
          mt: -8,
          mx: 3,
          py: 2,
          px: 2
        }}
      >
        <Grid container spacing={3} alignItems="center">
          <Grid item>
            <MDBox height="100%" mt={0.5} lineHeight={1}>
              <MDTypography variant="button" color="text" fontWeight="regular">
                Conectivitate server backend
              </MDTypography>
            </MDBox>
          </Grid>
          <Grid item>
            <MDAvatar bgColor={conStatus ? "success" : "error"} size="sm" shadow="sm" />
          </Grid>
          <Grid item xs={12} md={6} lg={4} sx={{ ml: "auto" }}>
            <MDButton onClick={checkConnectivity} color="info">
              Verifica conectivitate
            </MDButton>
          </Grid>
        </Grid>
      </Card>
      <Card
        sx={{
          position: "relative",
          mt: 1,
          mx: 3,
          py: 2,
          px: 2
        }}
      >
        <Grid container spacing={3} alignItems="center">
          <Grid item>
            <MDBox height="100%" mt={0.5} lineHeight={1}>
              <MDTypography variant="button" color="text" fontWeight="regular">
                Conectivitate raspberryPi
              </MDTypography>
            </MDBox>
          </Grid>
          <Grid item>
            <MDAvatar bgColor={raspberryConn ? "success" : "error"} size="sm" shadow="sm" />
          </Grid>
          <Grid item xs={12} md={6} lg={4} sx={{ ml: "auto" }}>
            <MDButton onClick={testRaspberryConnection} color="info">
              Verifica conectivitate
            </MDButton>
          </Grid>
        </Grid>
      </Card>
      <Grid>
        <Card
          sx={{
            position: "relative",
            mt: 2,
            mx: 3,
            py: 2,
            px: 2
          }}
        >
          <Grid item container spacing={2} alignItems="center">
            <Grid item xl={12}>
              <MDTypography variant="h6" textAlign="center">
                Pini Analogici
              </MDTypography>
            </Grid>
            <Grid item xl={12} container spacing={2} justifyContent="center">
              {analoguePins.map((pin, index) => (
                <Grid item key={index}>
                  <MDAvatar
                    sx={{
                      bgcolor: pin.free ? "success.main" : "error.main",
                      color: "#fff",
                      width: 50,
                      height: 50,
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                      fontSize: "1.2rem"
                    }}
                  >
                    {pin.pinNumber}
                  </MDAvatar>
                </Grid>
              ))}
            </Grid>
          </Grid>
        </Card>
        <Card
          sx={{
            position: "relative",
            mt: 2,
            mx: 3,
            py: 2,
            px: 2
          }}
        >
          <Grid item container spacing={2} alignItems="center">
            <Grid item xl={12}>
              <MDTypography variant="h6" textAlign="center">
                Pini Digitali
              </MDTypography>
            </Grid>
            <Grid item xl={12} container spacing={2} justifyContent="center">
              {digitalPins.map((pin, index) => (
                <Grid item key={index}>
                  <MDAvatar
                    sx={{
                      bgcolor: pin.free ? "success.main" : "error.main",
                      color: "#fff",
                      width: 50,
                      height: 50,
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                      fontSize: "1.2rem"
                    }}
                  >
                    {pin.pinNumber}
                  </MDAvatar>
                </Grid>
              ))}
            </Grid>
          </Grid>
        </Card>

        <Card
          sx={{
            position: "relative",
            mt: 2,
            mx: 3,
            py: 2,
            px: 2
          }}
        >
          <Grid item container spacing={2} alignItems="center">
            <Grid item xl={12}>
              <MDTypography variant="h6" textAlign="center">
                Pini Neconfigurati
              </MDTypography>
            </Grid>
            <Grid item xl={12} container spacing={2} justifyContent="center">
              {warningPins.map((pin, index) => (
                <Grid item key={index}>
                  <MDAvatar
                    onClick={() => handleOnClick(pin)}
                    sx={{
                      bgcolor: "warning.main",
                      color: "#fff",
                      width: 50,
                      height: 50,
                      display: "flex",
                      justifyContent: "center",
                      alignItems: "center",
                      fontSize: "1.2rem"
                    }}
                  >
                    {pin.pinNumber}
                  </MDAvatar>
                </Grid>
              ))}
            </Grid>
          </Grid>
        </Card>
        <Card
          sx={{
            position: "relative",
            mt: 2,
            mx: 3,
            py: 2,
            px: 2
          }}
        >
          <MDBox>
            <MDTypography variant="h5" textAlign="center">
              LEGENDA
            </MDTypography>
          </MDBox>
          <Grid container alignItems="center">
            <Grid item xl={4} container justifyContent="center">
              <PinsLegend description="Pin liber" collor="success.main" />
            </Grid>
            <Grid item xl={4} container justifyContent="center">
              <PinsLegend description="Pin cu semnal" collor="warning.main" />
            </Grid>
            <Grid item xl={4} container justifyContent="center">
              <PinsLegend description="Pin ocupat" collor="error.main" />
            </Grid>
          </Grid>
        </Card>
      </Grid>
    </MDBox>
  );
}

export default Status;
