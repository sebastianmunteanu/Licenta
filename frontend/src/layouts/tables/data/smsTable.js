import { useQueryClient } from "@tanstack/react-query";
import MDTypography from "components/MDTypography";
import { useSensorContext } from "context/SensorContext";
import { useEffect } from "react";

export default function smsTable() {
  const {
    state: { smsList }
  } = useSensorContext();
  const queryClient = useQueryClient();

  useEffect(() => {
    queryClient.invalidateQueries("smsList");
  }, []);

  const rows = Array.isArray(smsList)
    ? smsList?.map((sms) => ({
        message: (
          <MDTypography variant="highlight" color="dark">
            {sms.message}
          </MDTypography>
        ),
        phonenumber: (
          <MDTypography variant="highlight" color="dark">
            {sms.phoneNumber}
          </MDTypography>
        ),
        smsDate: (
          <MDTypography variant="highlight" color="dark">
            {sms.smsDate}
          </MDTypography>
        ),
        smsTime: (
          <MDTypography variant="highlight" color="dark">
            {sms.smsTime}
          </MDTypography>
        )
      }))
    : [];

  const columns = [
    { Header: "Mesaj", accessor: "message", width: "40%", align: "left" },
    { Header: "Numar de telefon", accessor: "phonenumber", width: "20%", align: "left" },
    { Header: "Data SMS", accessor: "smsDate", width: "20%", align: "left" },
    { Header: "Ora SMS", accessor: "smsTime", width: "20%", align: "left" }
  ];

  return {
    columns,
    rows
  };
}
