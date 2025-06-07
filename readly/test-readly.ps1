# test-readly.ps1

$headers = @{ "Content-Type" = "application/json" }

Write-Host "`n🔄 Resetting and Seeding Data..." -ForegroundColor Cyan

# Add Reader
$readerBody = @{
    name = "Alice"
    email = "alice@example.com"
} | ConvertTo-Json -Depth 10

$reader = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/readers" -Headers $headers -Body $readerBody
$readerId = $reader.id
Write-Host "✅ Created Reader ID: $readerId"

# Add Book
$bookBody = @{
    title = "1984"
    author = "George Orwell"
    genre = "Dystopian"
    pages = 328
    publishedDate = "1949-06-08"
} | ConvertTo-Json -Depth 10

$book = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/books" -Headers $headers -Body $bookBody
$bookId = $book.id
Write-Host "✅ Created Book ID: $bookId"

# Create ReadingEntry
$entryBody = @{
    readerId = $readerId
    bookId = $bookId
    status = "Reading"
    rating = 5
    review = "Great start"
    startedOn = "2025-06-01"
    finishedOn = $null
} | ConvertTo-Json -Depth 10

$entry = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/entries" -Headers $headers -Body $entryBody
$entryId = $entry.id
Write-Host "[CREATED] Created ReadingEntry ID: $entryId"

# Get all reading entries for this reader
Write-Host "`n📄 Fetching Entries for Reader $readerId..." -ForegroundColor Cyan
$entries = Invoke-RestMethod -Method GET -Uri "http://localhost:8080/api/entries/reader/$readerId"
$entries | ConvertTo-Json -Depth 10 | Out-String | Write-Host

# Update status to Finished
Write-Host "`n[UPDATING] Updating status to Finished..." -ForegroundColor Yellow
$updateBody = @{
    readerId = $readerId
    bookId = $bookId
    status = "Finished"
    rating = 5
    review = "Loved the ending"
    startedOn = "2025-06-01"
    finishedOn = "2025-06-07"
} | ConvertTo-Json -Depth 10

Invoke-RestMethod -Method PUT -Uri "http://localhost:8080/api/entries/$entryId" -Headers $headers -Body $updateBody

# Fetch updated entry
Write-Host "`n[UPDATED] Updated Entry:" -ForegroundColor Green
$updated = Invoke-RestMethod -Method GET -Uri "http://localhost:8080/api/entries/reader/$readerId/status/Finished"
$updated | ConvertTo-Json -Depth 10 | Out-String | Write-Host

# (Optional) Delete Entry
Invoke-RestMethod -Method DELETE -Uri "http://localhost:8080/api/entries/$entryId"
Write-Host "`n[DELETED] Entry deleted" -ForegroundColor Red
