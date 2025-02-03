import { useEffect, useState } from "react";
import { fetchWithAuth } from "../fetchWithAuth";

const DEFAULT_NAVBAR_DATA = {
  sensorNumber: 0,
  alertsToday: 0,
  smsToday: 0,
  inactiveSensors: 0
};

const fetchNavbarData = async () => {
  try {
    const response = await fetchWithAuth(`api/navbar-data`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    return {
      sensorNumber: data.sensorNumber,
      alertsToday: data.alertsToday,
      smsToday: data.smsToday,
      inactiveSensors: data.inactiveSensors
    };
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    return DEFAULT_NAVBAR_DATA;
  }
};

const useNavbarData = () => {
  const [navbarData, setNavbarData] = useState(DEFAULT_NAVBAR_DATA);
  useEffect(() => {
    fetchNavbarData().then(setNavbarData);
  }, []);
  return { navbarData };
};

export default useNavbarData;
