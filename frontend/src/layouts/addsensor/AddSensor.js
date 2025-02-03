import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

import MDBox from "components/MDBox";
import MDInput from "components/MDInput";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";

import {
  Card,
  FormControl,
  FormControlLabel,
  FormLabel,
  Grid,
  MenuItem,
  Radio,
  RadioGroup,
  Select
} from "@mui/material";
import MDButton from "components/MDButton";

function AddSensor() {
  const navigate = useNavigate();
  const {
    getSensorsPin,
    addSensor,
    setSelectedPin,
    state: { pinsByType, selectedPin }
  } = useSensorContext();

  const [selectedSensorType, setSelectedSensorType] = useState("");
  const [selectedSensorPin, setSelectedSensorPin] = useState("");
  const [name, setName] = useState("");
  const [place, setPlace] = useState("");
  const [onMessage, setOnMessage] = useState("");
  const [offMessage, setOffMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const [pinIsSelected, setPinIsSelected] = useState(false);

  const handleSensorTypeChange = (e) => {
    setSelectedSensorType(e.target.value);
    setSelectedSensorPin("");
    getSensorsPin(e.target.value);
  };

  const handleAddSensor = async () => {
    if (!name || !place || !onMessage || !offMessage || !selectedSensorPin || !selectedSensorType) {
      setErrorMessage("Aveti campuri necompletate");
      return;
    }

    const newSensor = {
      denumire: name,
      localizare: place,
      activeMessage: onMessage,
      inactiveMessage: offMessage,
      sensorPin: selectedSensorPin,
      sensorType: selectedSensorType
    };
    await addSensor(newSensor);
    navigate("/lista-senzori");
  };

  //La montare se verifica daca pinul este deja selectat
  //La demontare se seteaza pinul la {}
  useEffect(() => {
    if (Object.keys(selectedPin).length > 0) {
      setPinIsSelected(true);
      setSelectedSensorType(selectedPin.pinType);
      setSelectedSensorPin(selectedPin.id);
    }
    return () => {
      setSelectedPin({});
    };
  }, []);

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <Card>
        <MDBox pt={2} px={2} display="flex" justifyContent="center" alignItems="center">
          <MDTypography variant="h3" fontWeight="bold">
            Adauga senzor
          </MDTypography>
        </MDBox>
        <MDBox>
          <Grid>
            <MDBox
              bgColor=""
              borderRadius="lg"
              coloredShadow="success"
              mx={2}
              p={3}
              mb={1}
              textAlign="center"
            >
              <Grid container spacing={2}>
                <Grid item xs={12} md={12} lg={12}>
                  <MDTypography>Informatii despre senzor:</MDTypography>
                </Grid>
                <Grid item xs={12} md={6} lg={6}>
                  <MDBox mb={2}>
                    <MDInput
                      type="text"
                      label="Denumire senzor"
                      value={name}
                      onChange={(e) => setName(e.target.value)}
                      fullWidth
                    />
                  </MDBox>
                </Grid>
                <Grid item xs={12} md={6} lg={6}>
                  <MDBox mb={2}>
                    <MDInput
                      type="text"
                      label="Locatie"
                      value={place}
                      onChange={(e) => setPlace(e.target.value)}
                      fullWidth
                    />
                  </MDBox>
                </Grid>
                <Grid item xs={12} md={12} lg={12}>
                  <MDTypography>Mesaje:</MDTypography>
                </Grid>
                <Grid item xs={12} md={6} lg={6}>
                  <MDBox mb={2}>
                    <MDInput
                      type="text"
                      label="Senzor declansat"
                      value={onMessage}
                      onChange={(e) => setOnMessage(e.target.value)}
                      fullWidth
                    />
                  </MDBox>
                </Grid>
                <Grid item xs={12} md={6} lg={6}>
                  <MDBox mb={2}>
                    <MDInput
                      type="text"
                      label="Senzor nedeclansat"
                      value={offMessage}
                      onChange={(e) => setOffMessage(e.target.value)}
                      fullWidth
                    />
                  </MDBox>
                </Grid>
                <Grid item xs={12} md={12} lg={12}>
                  <MDTypography>Conectivitate:</MDTypography>
                </Grid>
                {pinIsSelected ? (
                  <Grid item container>
                    <Grid item container>
                      <MDTypography>Tipul senzorului:</MDTypography>
                      <MDTypography>{selectedPin.pinType}</MDTypography>
                    </Grid>
                    <Grid item container>
                      <MDTypography>Pinul conectat:</MDTypography>
                      <MDTypography>{selectedPin.pinDescription}</MDTypography>
                    </Grid>
                  </Grid>
                ) : (
                  <Grid item container>
                    <Grid item xs={12} md={6} lg={6}>
                      <MDBox mb={2} display="flex" justifyContent="center" alignItems="center">
                        <FormControl component="fieldset">
                          <FormLabel component="legend" sx={{ fontWeight: "bold", mb: 2 }}>
                            Selectează tipul senzorului:
                          </FormLabel>
                          <RadioGroup
                            row
                            value={selectedSensorType}
                            onChange={handleSensorTypeChange}
                          >
                            <FormControlLabel
                              value="analogic"
                              control={<Radio />}
                              label="Senzor Analogic"
                            />
                            <FormControlLabel
                              value="digital"
                              control={<Radio />}
                              label="Senzor Digital"
                            />
                          </RadioGroup>
                        </FormControl>
                      </MDBox>
                    </Grid>
                    <Grid container spacing={2} item xs={12} md={6} lg={6}>
                      <Grid item xs={12} md={6} lg={6}>
                        <MDTypography>Selecteaza Pinul:</MDTypography>
                      </Grid>
                      <Grid item xs={12} md={6} lg={6}>
                        <Select
                          value={selectedSensorPin}
                          onChange={(e) => setSelectedSensorPin(e.target.value)}
                          fullWidth
                          disabled={!selectedSensorType}
                        >
                          {pinsByType.map((pin) => (
                            <MenuItem key={pin.id} value={pin.id}>
                              {pin.pinDescription}
                            </MenuItem>
                          ))}
                        </Select>
                      </Grid>
                    </Grid>
                  </Grid>
                )}
              </Grid>
              {errorMessage && <MDBox color="error">{errorMessage}</MDBox>}
              <MDBox mt={3} display="flex" justifyContent="center">
                <MDButton color="info" onClick={handleAddSensor}>
                  Adaugă Senzor
                </MDButton>
              </MDBox>
            </MDBox>
          </Grid>
        </MDBox>
      </Card>
    </DashboardLayout>
  );
}

export default AddSensor;
