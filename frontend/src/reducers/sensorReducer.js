export const initialState = {
  sensors: [],
  currentSensor: {},
  editSensorEnable: false,
  isLoading: false,
  sensorsAlerts: [],
  smsAlerts: [],
  smsAlert: {},
  smsList: [],
  selectedPin: {},
  pins: [],
  pinsByType: []
};

export function sensorReducer(state, action) {
  //console.log("Action primită:", action);
  switch (action.type) {
    case "loading":
      return {
        ...state,
        isLoading: true
      };
    case "sensors/loaded":
      return {
        ...state,
        sensors: action.payload,
        isLoading: false
      };
    case "sensor/selected":
      return {
        ...state,
        currentSensor: action.payload,
        editSensorEnable: true
      };
    case "pin/selected":
      return {
        ...state,
        selectedPin: action.payload
      };
    case "sensor/alerts/fetched":
      return {
        ...state,
        smsAlerts: action.payload.alerts
      };
    case "sensor/edit/close":
      return {
        ...state,
        editSensorEnable: false
      };
    case "sensor/status/update":
      return {
        ...state,
        currentSensor: { ...state.currentSensor, status: action.payload.newStatus }
      };
    case "sensor/sms/add":
      return {
        ...state,
        smsAlerts: [...state.smsAlerts, action.payload]
      };
    case "sensor/sms/delete":
      return {
        ...state,
        smsAlerts: state.smsAlerts.filter((alert) => alert.id !== action.payload)
      };
    case "sensors/alerts/loaded":
      return {
        ...state,
        sensorsAlerts: action.payload.sensorsAlerts,
        isLoading: false
      };
    case "pins/set":
      return {
        ...state,
        pins: action.payload
      };
    case "sms/list/set":
      return {
        ...state,
        smsList: action.payload
      };
    case "sensor/pintype/selected":
      return {
        ...state,
        pinsByType: state.pins.filter((pin) => pin.pinType === action.payload)
      };
    default:
      return state;
  }
}
