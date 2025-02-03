import { Grid } from "@mui/material";
import MDAvatar from "components/MDAvatar";
import MDTypography from "components/MDTypography";
import PropTypes from "prop-types";

function PinsLegend({ description, collor }) {
  return (
    <Grid item xs={6} container direction="column" alignItems="center">
      <MDTypography variant="h6" textAlign="center">
        {description}
      </MDTypography>
      <MDAvatar
        sx={{
          bgcolor: collor,
          color: "#fff",
          width: 50,
          height: 50,
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          fontSize: "1.2rem",
          mt: 1
        }}
      >
        XX
      </MDAvatar>
    </Grid>
  );
}

PinsLegend.propTypes = {
  description: PropTypes.string,
  collor: PropTypes.string
};

export default PinsLegend;
