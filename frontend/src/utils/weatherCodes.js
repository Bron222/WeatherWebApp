// Open-Meteo reports conditions as WMO weather codes.
// Reference: https://open-meteo.com/en/docs (section "WMO Weather interpretation codes")
const WEATHER_CODES = {
  0: ['Clear sky', '☀️'],
  1: ['Mainly clear', '🌤️'],
  2: ['Partly cloudy', '⛅'],
  3: ['Overcast', '☁️'],
  45: ['Fog', '🌫️'],
  48: ['Freezing fog', '🌫️'],
  51: ['Light drizzle', '🌦️'],
  53: ['Drizzle', '🌦️'],
  55: ['Heavy drizzle', '🌦️'],
  56: ['Freezing drizzle', '🌧️'],
  57: ['Freezing drizzle', '🌧️'],
  61: ['Light rain', '🌧️'],
  63: ['Rain', '🌧️'],
  65: ['Heavy rain', '🌧️'],
  66: ['Freezing rain', '🌧️'],
  67: ['Freezing rain', '🌧️'],
  71: ['Light snow', '🌨️'],
  73: ['Snow', '🌨️'],
  75: ['Heavy snow', '🌨️'],
  77: ['Snow grains', '❄️'],
  80: ['Light showers', '🌦️'],
  81: ['Showers', '🌦️'],
  82: ['Heavy showers', '🌧️'],
  85: ['Snow showers', '🌨️'],
  86: ['Heavy snow showers', '🌨️'],
  95: ['Thunderstorm', '⛈️'],
  96: ['Thunderstorm with hail', '⛈️'],
  99: ['Thunderstorm with hail', '⛈️'],
}

export function describeWeather(code, isDay = true) {
  const [label, icon] = WEATHER_CODES[code] ?? ['Unknown', '❔']
  // Show a moon instead of a sun for clear nights.
  const nightIcon = code <= 1 && !isDay ? '🌙' : icon
  return { label, icon: nightIcon }
}
