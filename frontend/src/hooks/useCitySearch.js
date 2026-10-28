import { useQuery } from '@tanstack/react-query'
import { searchCities } from '../api/weatherApi'

export const MIN_QUERY_LENGTH = 2

export function useCitySearch(query) {
  const trimmed = query.trim()

  return useQuery({
    // The key identifies this data in the cache: same query text = reuse the result.
    queryKey: ['geocode', trimmed],
    queryFn: ({ signal }) => searchCities(trimmed, signal),
    enabled: trimmed.length >= MIN_QUERY_LENGTH,
    staleTime: 5 * 60 * 1000, // city search results rarely change
  })
}
