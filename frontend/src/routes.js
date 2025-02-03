import Connectivity from "layouts/conectivity/Connectivity";
import Dashboard from "layouts/dashboard";
import Alerts from "layouts/tables/alerts/Alerts";
import SensorsTable from "layouts/tables/sensors/SensorsTable";
import SmsList from "layouts/tables/sms/SmsTable";
// @mui icons
import Icon from "@mui/material/Icon";
import AddSensor from "layouts/addsensor/AddSensor";

const routes = [
  {
    type: "collapse",
    name: "Pagina principala",
    key: "dashboard",
    icon: <Icon fontSize="small">dashboard</Icon>,
    route: "/dashboard",
    component: <Dashboard />
  },
  {
    type: "collapse",
    name: "Senzori",
    key: "lista-senzori",
    icon: <Icon fontSize="small">table_view</Icon>,
    route: "/lista-senzori",
    component: <SensorsTable />
  },
  {
    type: "collapse",
    name: "Adauga sensor",
    key: "sensoradd",
    icon: <Icon fontSize="small">sensors</Icon>,
    route: "/adauga-senzor",
    component: <AddSensor />
  },
  {
    type: "collapse",
    name: "Alerte",
    key: "alerts",
    icon: <Icon fontSize="small">announcement</Icon>,
    route: "/lista-alerte",
    component: <Alerts />
  },
  {
    type: "collapse",
    name: "Alerte SMS",
    key: "alertssms",
    icon: <Icon fontSize="small">sms_gailed</Icon>,
    route: "/lista-sms",
    component: <SmsList />
  },
  {
    type: "collapse",
    name: "Conectivitate",
    key: "over",
    icon: <Icon fontSize="small">check</Icon>,
    route: "/status",
    component: <Connectivity />
  }
  // {
  //   type: "collapse",
  //   name: "Notifications",
  //   key: "notifications",
  //   icon: <Icon fontSize="small">notifications</Icon>,
  //   route: "/notifications",
  //   component: <Notifications />
  // },
  // {
  //   type: "collapse",
  //   name: "Billing",
  //   key: "billing",
  //   icon: <Icon fontSize="small">receipt_long</Icon>,
  //   route: "/billing",
  //   component: <Billing />
  // },

  // {
  //   type: "collapse",
  //   name: "Sign Up",
  //   key: "sign-up",
  //   icon: <Icon fontSize="small">assignment</Icon>,
  //   route: "/authentication/sign-up",
  //   component: <SingnUp />
  // }
];

export default routes;
