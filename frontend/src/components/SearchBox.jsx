import { useState } from 'react'
import { useDebouncedValue } from '../hooks/useDebouncedValue'
import { MIN_QUERY_LENGTH, useCitySearch } from '../hooks/useCitySearch'
import { placeDetail } from '../utils/placeDetail'

export default function SearchBox({ onSelect }) {
  const [query, setQuery] = useState('')
  const debouncedQuery = useDebouncedValue(query, 300)
  const { data: places, isLoading, isError, error } = useCitySearch(debouncedQuery)

  const showResults = query.trim().length >= MIN_QUERY_LENGTH

  function handleSelect(place) {
    onSelect(place)
    setQuery('') // clearing the box also closes the results list
  }

  return (
    <div className="search">
      <label htmlFor="city-search" className="visually-hidden">
        Search for a city
      </label>
      <input
        id="city-search"
        type="search"
        placeholder="Search for a city…"
        autoComplete="off"
        value={query}
        onChange={(event) => setQuery(event.target.value)}
      />

      {showResults && (
        <div className="search-results">
          {isLoading && <p className="status">Searching…</p>}
          {isError && <p className="status error">{error.message}</p>}
          {places?.length === 0 && <p className="status">No places found.</p>}
          {places?.length > 0 && (
            <ul>
              {places.map((place) => (
                <li key={place.id}>
                  <button type="button" onClick={() => handleSelect(place)}>
                    <span className="place-name">{place.name}</span>
                    <span className="place-detail">{placeDetail(place)}</span>
                  </button>
                </li>
              ))}
            </ul>
          )}
        </div>
      )}
    </div>
  )
}
