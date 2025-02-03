import PropTypes from "prop-types";

// @mui material components
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import { Divider } from "@mui/material";
import MDBadge from "components/MDBadge";
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";

function SensorEditStatus({ title }) {
  const {
    state: { currentSensor },
    updateSensorStatus
  } = useSensorContext();
  const handleClick = () => {
    updateSensorStatus();
  };
  return (
    <Card>
      <MDBox p={2} mx={3} display="flex" justifyContent="center">
        <MDBadge badgeContent={currentSensor.status} variant="gradient" size="lg" />
      </MDBox>
      <MDBox pb={2} px={2} textAlign="center" lineHeight={1.25}>
        <MDTypography variant="h6" fontWeight="medium" textTransform="capitalize">
          {title}
        </MDTypography>
        <Divider></Divider>
        <MDButton
          color={currentSensor.status === "ONLINE" ? "error" : "success"}
          onClick={handleClick}
        >
          {currentSensor.status === "ONLINE" ? "dezactiveaza" : "activeaza"}
        </MDButton>
      </MDBox>
    </Card>
  );
}

// Typechecking props for the DefaultInfoCard
SensorEditStatus.propTypes = {
  title: PropTypes.string
};

export default SensorEditStatus;
