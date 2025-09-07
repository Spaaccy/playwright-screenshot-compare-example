# 📸 TBC Pay Visual Test Task

## 🎯 Steps

1. **Navigate** 🌐 to https://tbcpay.ge/en/services/komerciuli-saitebi
2. **Take screenshot** 📷, save as `baseline.png`
3. **Open** `baseline.png` in Pixlr ✨
    - 🌐 Go to https://pixlr.com/editor/
    - 📂 Open photo
    - 🔧 Select cutout/mask tool
    - ⚙️ Set mode to "remove from mask"
    - 🎯 Select areas you want to ignore (header, footer, dynamic content)
    - ✅ You should see selected parts as checkered pattern
    - 💾 Export as full quality (**IMPORTANT**)
4. **Save** 💾 as `baseline-masked.png`
5. **In Playwright test** 🧪:
    - Take new screenshot of same page
    - Compare with `baseline-masked.png` using transparency masking
    - Verify comparison passes despite transparent areas

## 🎊 Expected Result

Test should **pass** ✅ even when transparent areas contain different content between baseline and current screenshots.

---

> 💡 **Tips**:
> - Focus on masking dynamic elements like timestamps, ads, or user-specific content that change between test runs
> - Use the project's existing comparison methods with transparency masking enabled
> - Check the test and main source directories for implementation examples
> - Organize screenshots in separate folders for baseline, current, and diff images