import MDBadge from "components/MDBadge";
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";

import { useQueryClient } from "@tanstack/react-query";
import { useSensorContext } from "context/SensorContext";
import { useEffect } from "react";

import MDButton from "components/MDButton";
import EditSensorButton from "components/MDButton/EditSensorButton";

export default function data(activeTab) {
  const {
    state: { sensors },
    deleteSensor
  } = useSensorContext();
  const queryClient = useQueryClient();

  useEffect(() => {
    queryClient.invalidateQueries("sensors");
  }, []);

  const rows = Array.isArray(sensors)
    ? sensors?.map((sensor) => ({
        name: (
          <MDTypography variant="highlight" color="dark">
            {sensor.name}
          </MDTypography>
        ),
        place: sensor.place,
        smsmembers: (
          <MDBox>
            {sensor.smsDestinatary.length > 0 ? (
              sensor.smsDestinatary.map((sms, index) => (
                <MDTypography key={index} variant="gradient" color="info" display="block">
                  {sms}
                </MDTypography>
              ))
            ) : (
              <MDTypography variant="gradient" color="error" display="block">
                Fara destinatari
              </MDTypography>
            )}
          </MDBox>
        ),
        status: (
          <MDBox>
            <MDBadge
              badgeContent={sensor.status}
              color={sensor.status === "ONLINE" ? "success" : "error"}
              variant="gradient"
              size="lg"
            />
          </MDBox>
        ),
        alertstoday: sensor.alertsToday,
        pin: sensor.sensorPin,
        edit: <EditSensorButton sensor={sensor} />,
        delete: (
          <MDButton
            variant="outlined"
            color="error"
            size="small"
            onClick={() => deleteSensor(sensor.id)}
          >
            Delete
          </MDButton>
        )
      }))
    : [];

  const columns = [
    { Header: "Tip senzor", accessor: "name", width: "20%", align: "left" },
    { Header: "localizare", accessor: "place", align: "left" },
    { Header: "Destinatari SMS", accessor: "smsmembers", width: "15%", align: "center" },
    { Header: "status", accessor: "status", align: "center" }
  ];

  if (activeTab === "dashboard") {
    columns.push({ Header: "numar alerte azi", accessor: "alertstoday", align: "center" });
  }

  if (activeTab === "table") {
    columns.push({ Header: "Pin", accessor: "pin", align: "center" });
    columns.push({ Header: "Stergere", accessor: "delete", align: "center" });
    columns.push({ Header: "Edit", accessor: "edit", align: "center" });
  }

  return {
    columns,
    rows
  };
}
