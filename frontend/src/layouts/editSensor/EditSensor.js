import { Grid } from "@mui/material";
import { fetchWithAuth } from "api/data/fetchWithAuth";
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import { useSensorContext } from "context/SensorContext";
import Messages from "./components/Messages";
import PhoneNumberAddAlert from "./components/PhoneNumberAddAlert";
import PhoneNumbers from "./components/PhoneNumbers";
import SensorCard from "./components/SensorCard";
import SensorEditStatus from "./components/SensorEditStatus";

function EditSensor() {
  const {
    state: { currentSensor }
  } = useSensorContext();

  const newAllert = async () => {
    try {
      await fetchWithAuth(`api/sensor/alert/${currentSensor.id}`, {
        method: "POST"
      });
    } catch {
      console.log("error");
    }
  };
  return (
    <>
      <MDBox m={3}>
        <Grid container spacing={2}>
          <MDButton color="info" onClick={() => newAllert()}>
            Genereaza alerta
          </MDButton>
        </Grid>
      </MDBox>
      <MDBox>
        <MDBox m={3}>
          <Grid container spacing={3}>
            <Grid item xs={12} lg={9}>
              <Grid container spacing={3}>
                <Grid item xs={12} xl={5} md={12}>
                  <SensorCard color={currentSensor.status === "ONLINE" ? "success" : "error"} />
                </Grid>
                <Grid item xs={12} md={6} xl={3}>
                  <SensorEditStatus title="Status actual" />
                </Grid>
                <Grid item xs={12} md={6} xl={4}>
                  <PhoneNumberAddAlert
                    icon="phone_number"
                    title="Add"
                    description="Seteaza alerta noua"
                  />
                </Grid>
                <Grid item xs={12}>
                  <Messages />
                </Grid>
              </Grid>
            </Grid>
            <Grid item xs={12} lg={3}>
              <PhoneNumbers />
            </Grid>
          </Grid>
        </MDBox>
      </MDBox>
    </>
  );
}

export default EditSensor;
