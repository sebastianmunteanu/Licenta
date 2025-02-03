import MDBox from "components/MDBox";

// Material Dashboard 2 React example components
import Footer from "examples/Footer";
import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Status from "./components/Status";

function Connectivity() {
  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox mb={2} />
      <Status></Status>
      <Footer />
    </DashboardLayout>
  );
}

export default Connectivity;
