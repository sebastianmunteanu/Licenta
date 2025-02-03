import { useQuery, useQueryClient } from "@tanstack/react-query";
import { getSensorsAlerts } from "api/data/alerts/getSensorAlerts";
import { getPinsByType } from "api/data/pin/getPinsByType";
import { addNewSensor } from "api/data/sensors/addNewSensor";
import { fetchSensorsData } from "api/data/sensors/getSensors";
import { deleteSensorById } from "api/data/sensors/sensorDelete";
import { updateOffMessage } from "api/data/sensors/updateOffMessage";
import { updateOnMessage } from "api/data/sensors/updateOnMessage";
import { updateStatus } from "api/data/sensors/updateStatus";
import { addSmsAlertToSensor } from "api/data/sms/addSmsAlertToSensor";
import { deleteSmsAlertFromSensor } from "api/data/sms/deleteSmsAlertFromSensor";
import { getAllSms } from "api/data/sms/getAllSms";
import { fetchSmsAlerts } from "api/data/sms/getSmsAlertsForSensor";
import PropTypes from "prop-types";
import { createContext, useContext, useEffect, useMemo, useReducer } from "react";
import { initialState, sensorReducer } from "reducers/sensorReducer";
import { useAuthContext } from "./AuthContext";
const SensorContext = createContext();

function SensorProvider({ children }) {
  const [state, dispatch] = useReducer(sensorReducer, initialState);
  const { isAuthenticated } = useAuthContext();
  const queryClient = useQueryClient();
  const { data: sensors = [] } = useQuery({
    queryKey: ["sensors"],
    queryFn: fetchSensorsData,
    enabled: isAuthenticated
  });

  useEffect(() => {
    if (sensors.length > 0) {
      dispatch({ type: "sensors/loaded", payload: sensors });
    }
  }, [sensors]);

  useQuery({
    queryKey: ["sensorsAlerts"],
    queryFn: getSensorsAlerts,
    enabled: isAuthenticated,
    onSuccess: (sensorsAlerts) => {
      dispatch({
        type: "sensors/alerts/loaded",
        payload: { sensorsAlerts }
      });
    }
  });

  const updateSensorStatus = async () => {
    const response = await updateStatus(state.currentSensor.id, state.currentSensor.status);
    if (response) {
      const newStatus = state.currentSensor.status === "ONLINE" ? "OFFLINE" : "ONLINE";
      dispatch({
        type: "sensor/status/update",
        payload: { newStatus }
      });
    } else {
      console.error("Failed to update sensor status");
    }
  };

  useQuery({
    queryKey: ["freePins"],
    queryFn: getPinsByType,
    enabled: isAuthenticated,
    onSuccess: (data) => {
      dispatch({
        type: "pins/set",
        payload: data
      });
    }
  });

  const getSensorsPin = async (pintype) => {
    dispatch({
      type: "sensor/pintype/selected",
      payload: pintype
    });
  };

  const handleSelectSensor = (sensor) => {
    dispatch({
      type: "sensor/selected",
      payload: { ...sensor }
    });
  };

  const setSelectedPin = (pin) => {
    dispatch({
      type: "pin/selected",
      payload: { ...pin }
    });
  };

  useQuery({
    queryKey: ["smsAlerts", state.currentSensor?.id],
    queryFn: () => fetchSmsAlerts(state.currentSensor?.id),
    enabled: Boolean(state.currentSensor?.id),
    onSuccess: (alerts) => {
      dispatch({
        type: "sensor/alerts/fetched",
        payload: { alerts }
      });
    }
  });

  const closeEdit = () => {
    dispatch({ type: "sensor/edit/close" });
    queryClient.invalidateQueries("sensors");
  };

  const addSmsAlert = async (pNumber) => {
    const { currentSensor } = state;
    const newSmsAlert = {
      sensorId: currentSensor.id,
      phoneNumber: pNumber
    };
    try {
      await addSmsAlertToSensor(newSmsAlert);
      queryClient.invalidateQueries(["smsAlerts", currentSensor.id]);
    } catch (error) {
      console.error("Eroare la adăugarea alertei SMS:", error);
    }
  };

  const editOnMessage = async (newOnMessage) => {
    const { currentSensor } = state;
    const respons = await updateOnMessage(currentSensor.id, newOnMessage);
    if (respons) {
      currentSensor.onMessage = newOnMessage;
    }
  };

  const editOffMessage = async (newOffMessage) => {
    const { currentSensor } = state;
    const respons = await updateOffMessage(currentSensor.id, newOffMessage);
    if (respons) {
      currentSensor.offMessage = newOffMessage;
    }
  };

  const deleteSmsAlert = async (id) => {
    const response = deleteSmsAlertFromSensor(id);
    if (response) {
      dispatch({
        type: "sensor/sms/delete",
        payload: id
      });
    }
  };

  const deleteSensor = async (id) => {
    const respons = await deleteSensorById(id);
    if (respons) {
      queryClient.invalidateQueries("sensors");
    }
  };

  const addSensor = async (newSensor) => {
    await addNewSensor(newSensor);
    queryClient.invalidateQueries("sensors");
  };

  useQuery({
    queryKey: ["smsList"],
    queryFn: getAllSms,
    enabled: isAuthenticated,
    onSuccess: (smsList) => {
      dispatch({
        type: "sms/list/set",
        payload: smsList
      });
    }
  });

  const contextValues = useMemo(() => {
    return {
      state,
      dispatch,
      updateSensorStatus,
      handleSelectSensor,
      closeEdit,
      addSmsAlert,
      editOnMessage,
      editOffMessage,
      deleteSmsAlert,
      getSensorsPin,
      deleteSensor,
      setSelectedPin,
      addSensor
    };
  }, [state, dispatch]);

  return <SensorContext.Provider value={contextValues}>{children}</SensorContext.Provider>;
}

function useSensorContext() {
  const context = useContext(SensorContext);
  if (context === undefined) throw new Error("nu este in context");
  return context;
}

SensorProvider.propTypes = {
  children: PropTypes.node
};

export { SensorProvider, useSensorContext };
