param (
    $IterationCount = 1
)

Set-StrictMode -Version 6
$ErrorActionPreference = 'Stop'

$currentId = 101
foreach ($x in 1..$IterationCount) {
    $objects = 1..100 | ForEach-Object {
        @{
            number = $currentId++
            name = 'Test product'
        }
    }
    $json = ConvertTo-Json $objects
    Write-Output "Sending request $x"
    Invoke-WebRequest -Uri http://localhost:8080/products -Method POST -Body $json -ContentType 'application/json'
}
