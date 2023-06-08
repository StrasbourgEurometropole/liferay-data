Set-ExecutionPolicy Unrestricted
git remote update origin --prune; git branch -vv | Select-String -Pattern ": gone]" | % { $_.toString().Trim().Split(" ")[0]} | % {git branch -d $_}
Read-Host -Prompt "Press Enter to exit"