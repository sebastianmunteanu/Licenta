import { fetchWithAuth } from "../fetchWithAuth";

const DEFAULT_NAVBAR_DATA = {
  smsAlerts: []
};

export const getAllSms = async () => {
  try {
    const response = await fetchWithAuth(`api/smsalert-all`);
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    return data.smsList;
  } catch (error) {
    console.error("There was a problem with the fetch operation:", error);
    return DEFAULT_NAVBAR_DATA;
  }
};
