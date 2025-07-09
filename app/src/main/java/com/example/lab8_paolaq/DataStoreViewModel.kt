package com.example.businesscard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DataStoreViewModel(application: Application) :
    AndroidViewModel(application) {

    private val _uiState = MutableStateFlow<DataStoreUiState>(
        DataStoreUiState())
    val uiState: StateFlow<DataStoreUiState> = _uiState

    init {
        getDataStoreValues()
    }

    fun getDataStoreValues() {
        viewModelScope.launch {
            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.NAME_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(
                            name = valueString
                        )
                    }
                }
            }
            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.ROLE_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(
                            role = valueString
                        )
                    }
                }
            }
            DataStoreInstance.getIntPreferences(
                getApplication(),
                DataStoreInstance.YEAR_KEY
            ).collect { valueInt ->
                if (valueInt != null) {
                    _uiState.update {
                        it.copy(
                            year = valueInt
                        )
                    }
                }
            }
            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.PHONE_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(
                            phone = valueString
                        )
                    }
                }
            }
            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.HANDLE_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(
                            handle = valueString
                        )
                    }
                }
            }
            DataStoreInstance.getStringPreferences(
                getApplication(),
                DataStoreInstance.EMAIL_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(
                            email = valueString
                        )
                    }
                }
            }
            DataStoreInstance.getBooleanPreferences(
                getApplication(),
                DataStoreInstance.BOOLEAN_KEY
            ).collect { valueString ->
                if (valueString != null) {
                    _uiState.update {
                        it.copy(
                            showContactInfo = valueString
                        )
                    }
                }
            }
        }
    }

    fun toggleSettings() {
        _uiState.update {
            it.copy(
                showSettings = _uiState.value.showSettings.not()
            )
        }
    }

    fun saveValuesInDataStore() {
        viewModelScope.launch {
            if (_uiState.value.isNameUpdated) {
                DataStoreInstance.saveStringPreferences(
                    getApplication(),
                    DataStoreInstance.NAME_KEY,
                    _uiState.value.name
                )
            }
            if (_uiState.value.isRoleUpdated) {
                DataStoreInstance.saveStringPreferences(
                    getApplication(),
                    DataStoreInstance.ROLE_KEY,
                    _uiState.value.role
                )
            }
            if (_uiState.value.isYearUpdated) {
                DataStoreInstance.saveIntPreferences(
                    getApplication(),
                    DataStoreInstance.YEAR_KEY,
                    _uiState.value.year
                )
            }
            if (_uiState.value.isPhoneUpdated) {
                DataStoreInstance.saveStringPreferences(
                    getApplication(),
                    DataStoreInstance.PHONE_KEY,
                    _uiState.value.phone
                )
            }
            if (_uiState.value.isHandleUpdated) {
                DataStoreInstance.saveStringPreferences(
                    getApplication(),
                    DataStoreInstance.HANDLE_KEY,
                    _uiState.value.handle
                )
            }
            if (_uiState.value.isEmailUpdated) {
                DataStoreInstance.saveStringPreferences(
                    getApplication(),
                    DataStoreInstance.EMAIL_KEY,
                    _uiState.value.email
                )
            }
            DataStoreInstance.saveBooleanPreferences(
                getApplication(),
                DataStoreInstance.BOOLEAN_KEY,
                _uiState.value.showContactInfo
            )
        }
    }

    fun updateName(value: String) {
        _uiState.update {
            it.copy(
                isNameUpdated = true,
                name = value
            )
        }
    }

    fun updateRole(value: String) {
        _uiState.update {
            it.copy(
                isRoleUpdated = true,
                role = value
            )
        }
    }

    fun updateYear(value: Int) {
        _uiState.update {
            it.copy(
                isYearUpdated = true,
                year = value
            )
        }
    }

    fun updatePhone(value: String) {
        _uiState.update {
            it.copy(
                isPhoneUpdated = true,
                phone = value
            )
        }
    }

    fun updateHandle(value: String) {
        _uiState.update {
            it.copy(
                isHandleUpdated = true,
                handle = value
            )
        }
    }

    fun updateEmail(value: String) {
        _uiState.update {
            it.copy(
                isEmailUpdated = true,
                email = value
            )
        }
    }

    fun toggleContactInfo() {
        _uiState.update {
            it.copy(
                showContactInfo = _uiState.value.showContactInfo.not()
            )
        }
    }
}

data class DataStoreUiState(
    val showSettings: Boolean = false,
    val name: String = "Jennifer Doe",
    val isNameUpdated: Boolean = false,
    val role: String = "Android Developer Extraordinarie",
    val isRoleUpdated: Boolean = false,
    val year: Int = 10,
    val isYearUpdated: Boolean = false,
    val phone: String = "+11 (123) 444 555 666",
    val isPhoneUpdated: Boolean = false,
    val handle: String = "@AndroidDev",
    val isHandleUpdated: Boolean = false,
    val email: String = "jen.doe@android.com",
    val isEmailUpdated: Boolean = false,
    val showContactInfo: Boolean = true
)