import Card from "@mui/material/Card";
// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";
import PhoneNumber from "./PhoneNumber";

function PhoneNumbers() {
  const {
    state: { smsAlerts }
  } = useSensorContext();
  return (
    <Card sx={{ height: "100%" }}>
      <MDBox display="flex" justifyContent="center" alignItems="center">
        <MDTypography variant="h6" fontWeight="medium">
          Alerte SMS
        </MDTypography>
      </MDBox>
      <MDBox>
        {smsAlerts.length > 0 ? (
          smsAlerts.map((smsAlert) => (
            <PhoneNumber phoneNumber={smsAlert.phoneNumber} key={smsAlert.id} id={smsAlert.id} />
          ))
        ) : (
          <MDTypography variant="gradient" verticalAlign="baseline" display="block">
            Fara destinatari
          </MDTypography>
        )}
      </MDBox>
    </Card>
  );
}

export default PhoneNumbers;
