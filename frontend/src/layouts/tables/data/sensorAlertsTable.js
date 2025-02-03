import { useQueryClient } from "@tanstack/react-query";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";
import { useEffect } from "react";

export default function sensorsAlerts() {
  const {
    state: { sensorsAlerts }
  } = useSensorContext();
  const queryClient = useQueryClient();

  useEffect(() => {
    queryClient.invalidateQueries("sensorAlerts");
  }, []);
  const rows = Array.isArray(sensorsAlerts)
    ? sensorsAlerts?.map((sensorAlerts) => ({
        name: (
          <MDTypography variant="highlight" color="dark">
            {sensorAlerts.eventName}
          </MDTypography>
        ),
        localizare: (
          <MDTypography variant="highlight" color="dark">
            {sensorAlerts.eventPlace}
          </MDTypography>
        ),
        eventDate: (
          <MDTypography variant="highlight" color="dark">
            {sensorAlerts.eventDate}
          </MDTypography>
        ),
        eventTime: (
          <MDTypography variant="highlight" color="dark">
            {sensorAlerts.eventTime}
          </MDTypography>
        )
      }))
    : [];

  const columns = [
    { Header: "Nume eveniment", accessor: "name", width: "20%", align: "left" },
    { Header: "Adresa", accessor: "localizare", width: "20%", align: "left" },
    { Header: "Data eveniment", accessor: "eventDate", width: "20%", align: "left" },
    { Header: "Ora eveniment", accessor: "eventTime", width: "20%", align: "left" }
  ];

  return {
    columns,
    rows
  };
}
