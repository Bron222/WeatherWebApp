// All calls to our Spring Boot backend live here, so components never build URLs themselves.

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL ?? 'http://localhost:8080'

async function getJson(path, params, signal) {
  const url = new URL(path, API_BASE_URL)
  url.search = new URLSearchParams(params).toString()

  let response
  try {
    response = await fetch(url, { signal })
  } catch (err) {
    // TanStack Query cancels requests it no longer needs; let that through untouched.
    if (err.name === 'AbortError') throw err
    // Friendly message for the UI; the original error stays attached for debugging.
    throw new Error("Can't reach the server. Is the backend running?", { cause: err })
  }

  if (!response.ok) {
    // The backend's GlobalExceptionHandler returns { status, error, message }.
    const body = await response.json().catch(() => null)
    throw new Error(body?.message ?? `Request failed (${response.status})`)
  }

  return response.json()
}

export async function searchCities(query, signal) {
  const data = await getJson('/api/geocode', { q: query }, signal)
  return data.results ?? []
}

export function fetchWeather(latitude, longitude, signal) {
  return getJson('/api/weather', { lat: latitude, lon: longitude }, signal)
}
