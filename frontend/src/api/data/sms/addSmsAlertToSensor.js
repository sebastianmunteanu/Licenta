import { fetchWithAuth } from "../fetchWithAuth";

export const addSmsAlertToSensor = async (newSmsAlert) => {
  try {
    const response = await fetchWithAuth(`api/addalertsmstosensor`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(newSmsAlert)
    });
    return response.ok;
  } catch {
    console.log("error");
  }
};
