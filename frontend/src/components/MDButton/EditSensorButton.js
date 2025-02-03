import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import { useSensorContext } from "context/SensorContext";
import PropTypes from "prop-types";

function EditSensorButton({ sensor }) {
  const { handleSelectSensor } = useSensorContext();

  const handleEditClick = () => {
    handleSelectSensor(sensor);
  };
  return (
    <MDBox display="flex" alignItems="center">
      <MDButton variant="outlined" color="info" size="small" onClick={() => handleEditClick()}>
        Edit senzor
      </MDButton>
    </MDBox>
  );
}

EditSensorButton.propTypes = {
  sensor: PropTypes.object.isRequired
};

export default EditSensorButton;
