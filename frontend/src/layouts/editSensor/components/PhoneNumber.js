import PropTypes from "prop-types";

// @mui material components
import Icon from "@mui/material/Icon";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";

function PhoneNumber({ phoneNumber, id, noGutter }) {
  const { deleteSmsAlert } = useSensorContext();
  const handleClick = async () => {
    await deleteSmsAlert(id);
  };
  return (
    <MDBox
      component="li"
      display="flex"
      justifyContent="space-between"
      alignItems="center"
      py={1}
      pr={1}
      mb={noGutter ? 0 : 1}
      m={1}
    >
      <MDBox lineHeight={1.125}>
        <MDTypography display="block" variant="button" fontWeight="medium">
          {phoneNumber}
        </MDTypography>
        <Icon fontSize="small">sms</Icon>
      </MDBox>
      <MDBox display="flex" alignItems="center">
        <MDButton ml={3} color="info" size="small" onClick={handleClick}>
          delete
        </MDButton>
      </MDBox>
    </MDBox>
  );
}

PhoneNumber.defaultProps = {
  noGutter: false
};

PhoneNumber.propTypes = {
  phoneNumber: PropTypes.string,
  id: PropTypes.number.isRequired,
  noGutter: PropTypes.bool
};

export default PhoneNumber;
