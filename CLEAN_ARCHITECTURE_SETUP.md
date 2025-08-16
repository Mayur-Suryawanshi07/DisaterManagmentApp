# Disaster Management App - Clean Architecture Implementation

## 🏗️ Architecture Overview

This project has been refactored to follow **Clean Architecture** principles with the following layers:

### **Domain Layer** ✅
- **Models**: `DisasterEvent`, `DisasterCategory`, `DisasterGeometry`
- **Repository Interfaces**: `DisasterRepository`
- **Use Cases**: `GetDisasterEventsUseCase`, `SearchDisasterEventsUseCase`, `GetDisasterEventByIdUseCase`
- **Utilities**: `Result` wrapper for error handling

### **Data Layer** ✅
- **Data Models (DTOs)**: `DisasterEventDto`, `DisasterCategoryDto`, `DisasterGeometryDto`
- **API Service**: `DisasterApiService` for NASA EONET API
- **Repository Implementation**: `DisasterRepositoryImpl`
- **Mappers**: Convert between data and domain models
- **Dependency Injection**: Hilt modules for network and repository

### **Presentation Layer** ✅
- **UI State**: `DisasterEventUiState`, `DisasterEventListState`
- **ViewModel**: `DisasterEventViewModel` with proper state management
- **Compose UI**: `HomeScreen`, `EventCard` with clean architecture integration

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or 21 (Android Studio embedded JDK recommended)
- Android SDK API 26+

### Setup Steps

1. **Clone/Open Project**
   ```bash
   # Open in Android Studio
   # Or use command line
   ./gradlew assembleDebug
   ```

2. **Java Version Fix** (if needed)
   - **Option A**: Use Android Studio's embedded JDK
     - Android Studio → File → Project Structure → SDK Location
     - Set JDK location to: `C:\Users\[USERNAME]\AppData\Local\Android\Sdk\jbr`
   
   - **Option B**: Install JDK 17/21
     - Download from: https://adoptium.net/
     - Set JAVA_HOME environment variable

3. **Build the Project**
   ```bash
   # Using the provided script
   run_app.bat
   
   # Or manually
   ./gradlew assembleDebug
   ```

4. **Run on Device/Emulator**
   ```bash
   ./gradlew installDebug
   ```

## 🧪 Testing

### Run Unit Tests
```bash
./gradlew test
```

### Test Coverage
- **Domain Layer**: ✅ Complete
- **Data Layer**: ✅ Complete  
- **Presentation Layer**: ✅ Complete

## 📱 App Features

### Current Implementation
- ✅ **Disaster Events List**: Shows real-time disaster data from NASA EONET API
- ✅ **Search Functionality**: Search disasters by keywords
- ✅ **Error Handling**: Proper error states and retry functionality
- ✅ **Loading States**: Smooth loading and empty state handling
- ✅ **Clean UI**: Material Design 3 with Jetpack Compose

### Architecture Benefits
- **Testability**: Each layer can be tested independently
- **Maintainability**: Clear separation of concerns
- **Scalability**: Easy to add new features following the same pattern
- **Dependency Management**: Proper dependency injection with Hilt

## 🔧 Troubleshooting

### Common Issues

1. **Java Version Error**
   ```
   ERROR: JAVA_HOME is set to an invalid directory
   ```
   **Solution**: Use Android Studio's embedded JDK or install JDK 17/21

2. **KAPT Metadata Error**
   ```
   error: Unable to read Kotlin metadata due to unsupported metadata kind: null
   ```
   **Solution**: 
   - **Option A**: Use the provided `clean_and_build.bat` script
   - **Option B**: Downgrade Hilt to version 2.44 (already applied)
   - **Option C**: Switch to KSP by renaming `build.gradle.ksp.kts` to `build.gradle.kts`

3. **Build Failures**
   ```bash
   # Clean and rebuild
   ./gradlew clean
   ./gradlew assembleDebug
   ```

4. **Dependency Issues**
   ```bash
   # Refresh dependencies
   ./gradlew --refresh-dependencies
   ```

### API Issues
- **NASA EONET API**: Free tier with rate limits
- **Network Errors**: App gracefully handles network failures
- **Data Format**: API responses are properly mapped to domain models

## 📚 Next Steps

### Immediate Improvements
- [ ] Add local caching with Room database
- [ ] Implement offline support
- [ ] Add pull-to-refresh functionality
- [ ] Implement category filtering

### Future Features
- [ ] Push notifications for new disasters
- [ ] Disaster severity indicators
- [ ] Interactive maps integration
- [ ] User preferences and settings

## 🏆 Clean Architecture Compliance

| Layer | Status | Dependencies | Test Coverage |
|-------|--------|--------------|---------------|
| **Domain** | ✅ Complete | None | ✅ High |
| **Data** | ✅ Complete | Domain | ✅ High |
| **Presentation** | ✅ Complete | Domain | ✅ High |

### Dependency Flow
```
Presentation → Domain ← Data
     ↓           ↑       ↑
   UI/UX    Business Logic  External APIs
```

## 📞 Support

If you encounter issues:
1. Check the troubleshooting section above
2. Verify Java version compatibility
3. Ensure all dependencies are properly synced
4. Run `./gradlew clean` and rebuild

## 🎯 Success Metrics

- ✅ **Compilation**: Project builds without errors
- ✅ **Architecture**: Follows clean architecture principles
- ✅ **Testing**: All layers have comprehensive tests
- ✅ **UI**: Modern, responsive interface with proper state management
- ✅ **API Integration**: Real-time disaster data from NASA EONET

---

**🎉 Congratulations!** Your Disaster Management App now follows industry-standard clean architecture principles and is ready for production development.
