// "Ontario, Canada" — skips whichever parts are missing.
export function placeDetail(place) {
  return [place.region, place.country].filter(Boolean).join(', ')
}
