// @mui material components
import Grid from "@mui/material/Grid";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";

// Material Dashboard 2 React example components
import ComplexStatisticsCard from "examples/Cards/StatisticsCards/ComplexStatisticsCard";
import ReportsBarChart from "examples/Charts/BarCharts/ReportsBarChart";
import ReportsLineChart from "examples/Charts/LineCharts/ReportsLineChart";
import Footer from "examples/Footer";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";

// Dashboard data from api
import useWeekChartData from "api/data/chart/weekChartData";
import useYearChartData from "api/data/chart/yearChartData";
import useNavbarData from "api/data/navbar/navbarData";

// Dashboard components
import SensorsTableDashboars from "layouts/tables/sensors/SensorsTableDashboars";

function Dashboard() {
  const { weekChartData } = useWeekChartData();
  const { yearChartData } = useYearChartData();
  const { navbarData } = useNavbarData();
  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox py={3}>
        <Grid container spacing={3}>
          <Grid item xs={12} md={6} lg={3}>
            <MDBox mb={1.5}>
              <ComplexStatisticsCard
                color="success"
                icon="sensors"
                title="Numar senzori"
                count={navbarData.sensorNumber}
              />
            </MDBox>
          </Grid>
          <Grid item xs={12} md={6} lg={3}>
            <MDBox mb={1.5}>
              <ComplexStatisticsCard
                color="primary"
                icon="sensors_off"
                title="Senzori inactivi"
                count={navbarData.inactiveSensors}
              />
            </MDBox>
          </Grid>
          <Grid item xs={12} md={6} lg={3}>
            <MDBox mb={1.5}>
              <ComplexStatisticsCard
                icon="crisis_alert"
                title="Alerte primite de la senzori"
                count={navbarData.alertsToday}
              />
            </MDBox>
          </Grid>
          <Grid item xs={12} md={6} lg={3}>
            <MDBox mb={1.5}>
              <ComplexStatisticsCard
                color="info"
                icon="sms"
                title="Alerte transmise prin SMS"
                count={navbarData.smsToday}
              />
            </MDBox>
          </Grid>
        </Grid>
        <MDBox mt={4.5}>
          <Grid container spacing={3}>
            <Grid item xs={12} md={12} lg={4}>
              <MDBox mb={3}>
                <ReportsBarChart
                  color="info"
                  title="Alerte"
                  description="Alerte in ultimele 7 zile"
                  date=""
                  chart={weekChartData}
                />
              </MDBox>
            </Grid>
            <Grid item xs={12} md={12} lg={8}>
              <MDBox mb={3}>
                <ReportsLineChart
                  color="success"
                  title="Alerte"
                  description="Alerte in ultimul an"
                  chart={yearChartData}
                />
              </MDBox>
            </Grid>
          </Grid>
        </MDBox>
        <MDBox>
          <Grid container spacing={3}>
            <Grid item xs={12} md={12} lg={12}>
              <SensorsTableDashboars />
            </Grid>
          </Grid>
        </MDBox>
      </MDBox>
      <Footer />
    </DashboardLayout>
  );
}

export default Dashboard;
