import os, json

# user_email = 'pratikchachpara@gmail.com'
# user_pass = 'pratikmusicly'
username_to_be_searched = 'test'
no_of_fans_to_be_commented = '4'
# fan1 = '@beangwen5558'
# fan2 = '@justfunbea'
# fan3 = '@test927'
# comment_text='HelloThisIsCommentSample'
# commnt_icon_x_location = '1008'
# commnt_icon_y_location = '1146'

print("1) Chagning the configuration values.....")
# os.system("sed -i '' 's/valid.email=pratikchachpara@gmail.com/valid.email="+user_email+"/g' src/test/resources/configuration.properties")
# os.system("sed -i '' 's/valid.password=pratikmusicly/valid.password="+user_pass+"/g' src/test/resources/configuration.properties")
os.system("sed -i 's/search.username=username/search.username="+username_to_be_searched+"/g' src/test/resources/configuration.properties")
os.system("sed -i 's/search.username.fan.count=4/search.username.fan.count="+no_of_fans_to_be_commented+"/g' src/test/resources/configuration.properties")
# os.system("sed -i '' 's/username.fan1=@ashbare22/username.fan1="+fan1+"/g' src/test/resources/configuration.properties")
# os.system("sed -i '' 's/username.fan2=@piggylover4122007/username.fan2="+fan2+"/g' src/test/resources/configuration.properties")
# os.system("sed -i '' 's/username.fan3=@lxdanielxl/username.fan3="+fan3+"/g' src/test/resources/configuration.properties")
# os.system("sed -i '' 's/comment.text=Hello123/comment.text="+comment_text+"/g' src/test/resources/configuration.properties")
# os.system("sed -i '' 's/Pixel.comment.icon_x=1007/Pixel.comment.icon_x="+commnt_icon_x_location+"/g' src/test/resources/configuration.properties")
# os.system("sed -i '' 's/Pixel.comment.icon_y=1145/Pixel.comment.icon_y="+commnt_icon_y_location+"/g' src/test/resources/configuration.properties")

print("2) Packaging the Test cases Jar....")
os.system('mvn clean package -DskipTests=true')

print("3) Uploading test cases to AWS....")
os.system('aws devicefarm create-upload --project-arn arn:aws:devicefarm:us-west-2:871290565691:project:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa --type APPIUM_JAVA_TESTNG_TEST_PACKAGE --name musically.zip > responseurl.json')
with open('responseurl.json') as data_file:    
		data = json.load(data_file)
url = data['upload']['url']
print("Url upon which .zip will be uploaded--> "+url)		

os.system('curl -T target/zip-with-dependencies.zip "'+url+'"')

print("4) Getting the status of uploaded Zip file....")
status = False
arn_of_uploaded_zip = ''
while status == False:
	os.system("aws devicefarm get-upload --arn arn:aws:devicefarm:us-west-2:871290565691:upload:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/35122ac7-aca7-47b0-888e-ae44e3877555>zip_status.json")
	with open('zip_status.json') as data_file:    
		data = json.load(data_file)
	upload_status = data['upload']['status']	
	if upload_status=='SUCCEEDED':
		status = True
		arn_of_uploaded_zip = data['upload']['arn']
		print('build SUCCEEDED, ARN: '+arn_of_uploaded_zip)

# arn_of_uploaded_zip = "arn:aws:devicefarm:us-west-2:871290565691:upload:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/ac78e709-57dc-4b54-aa88-4883f25f2a52"
print("5) Scheduling run on AWS Device Farm.....")
schedule_run_command = 'aws devicefarm schedule-run --project-arn arn:aws:devicefarm:us-west-2:871290565691:project:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa --app-arn arn:aws:devicefarm:us-west-2:871290565691:upload:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/fa1fcb61-4097-4a65-8bdf-c30c0dc71178 --device-pool-arn arn:aws:devicefarm:us-west-2:871290565691:devicepool:08ddbcf6-51e1-4c6c-9cee-6505d7b1d8aa/b5a5925b-a9d7-4759-9202-cf986d4f4358 --name firstMusicalLy --test ' +  '\'{"type": "APPIUM_JAVA_TESTNG","testPackageArn":"'+ arn_of_uploaded_zip+'"}\''
os.system(schedule_run_command)


print("6) Congratulations. Build started on AWS..!")

print("7) Resetting the changes made to configuration.properties file..")
os.system('git checkout -- src/test/resources/configuration.properties')

print("DONE!")


