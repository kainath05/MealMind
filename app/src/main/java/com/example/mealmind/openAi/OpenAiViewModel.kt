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
    val responses = mutableStateOf<List<String>>(emptyList()) // Store multiple outputs
    val responseText = mutableStateOf("") // Store single response for detailed views

    fun getResponse(cuisine: String, dietaryRestrictions: String, mealType: String) {
        val prompt = """
        Generate 5 unique recipes for a $cuisine dish considering the following:
        - Dietary Restrictions: $dietaryRestrictions
        - Meal Type: $mealType.
        Display only the names of the recipes, each on a new line.
    """.trimIndent()

        viewModelScope.launch {
            try {
                val request = ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(ChatMessage(role = ChatRole.User, content = prompt))
                )
                val completion = openAI.chatCompletion(request)
                val fullResponse = completion.choices.firstOrNull()?.message?.content ?: "No response."

                // Split the response by newlines and filter out empty strings
                val recipes = fullResponse
                    .split("\n")
                    .map { it.trim() }
                    .filter { it.isNotBlank() }
                    .take(5) // Take only the first 5 names

                responses.value = recipes // Update the state with the list of recipe names
            } catch (e: Exception) {
                responses.value = listOf("Error: ${e.localizedMessage}")
            }
        }
    }

    fun getRecipe(recipeName: String) {
        val prompt = "Generate the recipe for $recipeName."

        viewModelScope.launch {
            try {
                val request = ChatCompletionRequest(
                    model = ModelId("gpt-3.5-turbo"),
                    messages = listOf(ChatMessage(role = ChatRole.User, content = prompt))
                )
                val completion = openAI.chatCompletion(request)
                val response = completion.choices.firstOrNull()?.message?.content ?: "No response."
                responseText.value = response // Update the state with the response
            } catch (e: Exception) {
                responseText.value = "Error: ${e.localizedMessage}"
            }
        }
    }
}


