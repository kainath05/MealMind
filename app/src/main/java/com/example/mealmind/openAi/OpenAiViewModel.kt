package com.example.mealmind.openAi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.client.OpenAI
import com.aallam.openai.api.model.ModelId
import kotlinx.coroutines.launch

// REFERENCES:
// Open AI docs: https://platform.openai.com/docs/guides/text-generation

@OptIn(BetaOpenAI::class)
class OpenAiViewModel : ViewModel() {


    private val openAI = OpenAI("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")
    val responseText = mutableStateOf("")


    fun getResponse(ingredients: String, cuisine: String) {

        val prompt = "Generate a recipe for a $cuisine dish using the following ingredients: $ingredients."

        viewModelScope.launch {
            val request = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.User,
                        content = prompt
                    )
                )
            )

            // Send request and update responseText with the result
            try {
                val completion = openAI.chatCompletion(request)
                val response = completion.choices.firstOrNull()?.message?.content ?: "No response."
                responseText.value = response
            } catch (e: Exception) {
                responseText.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}
