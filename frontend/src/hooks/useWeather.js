import { useQuery } from '@tanstack/react-query'
import { fetchWeather } from '../api/weatherApi'

export function useWeather(place) {
  return useQuery({
    queryKey: ['weather', place?.latitude, place?.longitude],
    queryFn: ({ signal }) => fetchWeather(place.latitude, place.longitude, signal),
    enabled: place != null, // don't fetch until a city has been picked
    staleTime: 10 * 60 * 1000,
  })
}
