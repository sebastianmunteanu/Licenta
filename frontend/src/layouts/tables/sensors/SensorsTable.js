import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDButton from "components/MDButton";
import MDTypography from "components/MDTypography";

// Material Dashboard 2 React example components
import Footer from "examples/Footer";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import DataTable from "examples/Tables/DataTable";
import { useEffect } from "react";

import { useSensorContext } from "context/SensorContext";
import EditSensor from "layouts/editSensor/EditSensor";
import data from "layouts/tables/data/sensorsTableData";

// Data

function SensorsTable() {
  const { columns, rows } = data("table");
  const {
    state: { editSensorEnable },
    closeEdit
  } = useSensorContext();

  useEffect(() => {
    return () => {
      closeEdit();
    };
  }, []);

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox pt={6} pb={3}>
        <Grid container spacing={6}>
          {editSensorEnable ? (
            <Grid item xs={12}>
              <MDBox m={1}>
                <MDButton color="info" onClick={() => closeEdit()}>
                  Exit
                </MDButton>
              </MDBox>
              <Card>
                <EditSensor />
              </Card>
            </Grid>
          ) : (
            <Grid item xs={12}>
              <Card>
                <MDBox
                  mx={2}
                  mt={-3}
                  py={3}
                  px={2}
                  variant="gradient"
                  bgColor="info"
                  borderRadius="lg"
                  coloredShadow="info"
                >
                  <MDTypography variant="h6" color="white">
                    Senzori
                  </MDTypography>
                </MDBox>
                <MDBox pt={3}>
                  <DataTable
                    table={{ columns, rows }}
                    isSorted={false}
                    entriesPerPage={false}
                    showTotalEntries={false}
                    noEndBorder
                  />
                </MDBox>
              </Card>
            </Grid>
          )}
        </Grid>
      </MDBox>
      {editSensorEnable ? "" : <Footer />}
    </DashboardLayout>
  );
}

export default SensorsTable;
